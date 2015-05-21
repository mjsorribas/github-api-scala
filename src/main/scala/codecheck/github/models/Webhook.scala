package codecheck.github.models

import org.json4s.JValue

class Webhook(value: JValue) extends AbstractJson(value) {
  def id: Long = get("id").toLong
  def url = get("url")
  def test_url = get("test_url")
  def ping_url = get("ping_url")
  def name = get("name")
  def events = seq("events")
  def active = boolean("active")
  def config = new WebhookConfig(get("config.url"), get("config.content_type"), get("config.secret"), get("config.insecure_ssl"));
  def last_response = new WebhookResponse(value \ "last_response")
  def updated_at = date("updated_at")
  def created_at = date("created_at")
}

case class WebhookConfig(
  url: String,
  content_type: String = "json",
  secret: String = "",
  insecure_ssl: String = "0"
  ) extends AbstractInput 

case class WebhookCreateInput(
  name: String,
  config: WebhookConfig,
  active: Boolean = true,
  events: Seq[String] = Seq("push"),
  add_events: Seq[String] = Nil,
  remove_events: Seq[String] = Nil
  ) extends AbstractInput

case class WebhookUpdateInput(
  config: Option[WebhookConfig] = None,
  events: Option[Seq[String]] = None,
  add_events: Option[Seq[String]] = None,
  remove_events: Option[Seq[String]] = None,
  active: Option[Boolean] = None
  ) extends AbstractInput

case class WebhookResponse(value: JValue) extends AbstractJson(value) {
  def code = get("code")
  def status = get("status")
  def message = get("message")
}